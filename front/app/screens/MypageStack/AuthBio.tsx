import BtnBox from "../../components/BtnBox";
import {
  ContentContainer,
  ContentFooterContainer,
  FooterContainer,
  HeaderText,
  LayoutContainer,
  QRcodeContainer,
  QRcodeInfoNameText,
} from "../styled";
import * as Application from "expo-application";
import * as Device from "expo-device";
import styled from "styled-components/native";
import { Alert } from "react-native";
import AsyncStorage from "@react-native-async-storage/async-storage";
import axios from "axios";

interface PropsType {
  // stack navigation
  navigation: any;
  // 2차 인증 정보 등록 여부
  auth: any;
  // 새로운 인증 토큰 발급
  getNewToken: () => Promise<any>;
  // 2차 인증 정보 등록 여부 확인
  getMyAuth: () => void;
}

// Component _ AuthBio
const AuthBio: React.FC<PropsType> = ({
  navigation,
  auth,
  getNewToken,
  getMyAuth,
}) => {
  // const
  // Axios url
  const bioUrl = "http://k6s101.p.ssafy.io:8080/pay/auth/bio";

  // 안드로이드 앱 서명 키
  const androidId = Application.androidId;
  // 기기 모델 명
  const modelName = Device.modelName;
  // 애플 앱 서명 키
  const idData =
    androidId === null ? Application.getIosIdForVendorAsync() : androidId;
  // 데이터
  const bioData = { id: idData, model: modelName };

  // Axios
  // 생체인증 정보 등록
  const bioPost = async () => {
    const accessToken = await AsyncStorage.getItem("accessToken");
    await axios({
      method: "post",
      url: bioUrl,
      headers: { Authorization: `Bearer ${accessToken}` },
      data: bioData,
    })
      .then((res) => {
        console.log(res.data);
        Alert.alert("알림", `${bioData.model}을 등록하였습니다.`);
        getMyAuth();
      })
      .catch(async (err) => {
        console.log(err);
        if (err.response.status === 401 && (await getNewToken())) {
          bioPost();
        } else {
          Alert.alert("등록에 실패하였습니다.");
        }
      });
  };
  // 생체인증 정보 삭제
  const bioDel = async () => {
    console.log("del");
    const accessToken = await AsyncStorage.getItem("accessToken");
    await axios({
      method: "delete",
      url: bioUrl,
      headers: { Authorization: `Bearer ${accessToken}` },
    })
      .then((res) => {
        console.log(res);
        console.log("생체 인증 정보 삭제 성공!");
        bioPost();
      })
      .catch(async (err) => {
        console.log(err);
        if (err.response.status === 401 && (await getNewToken?.())) {
          bioDel();
        } else {
          Alert.alert("등록에 실패하였습니다.");
        }
      });
  };

  // method
  const registBio = () => {
    if (auth.bio) {
      Alert.alert("알림", "이미 등록된 기기를 삭제하고 등록하시겠습니까?", [
        {
          text: "아니요",
          onPress: () => console.log("Cancel Pressed"),
          style: "cancel",
        },
        {
          text: "네",
          onPress: () => {
            bioDel();
          },
        },
      ]);
    } else {
      bioPost();
    }
  };

  return (
    <LayoutContainer>
      <HeaderText>생체인증 정보 등록</HeaderText>
      <ContentFooterContainer>
        <ContentContainer>
          <DataContainer>
            <DataText>
              현재 등록된 모델 : {auth.bio ? bioData.model : "-"}
            </DataText>
            <DataTextDetail>
              {auth.bio
                ? "아이디당 하나의 모델만 등록 가능합니다."
                : "기기를 등록해주세요."}
            </DataTextDetail>
          </DataContainer>
        </ContentContainer>
        <FooterContainer>
          <BtnBox
            color="blue"
            text="등록하기"
            navigation={navigation}
            setter={registBio}
          />
          <BtnBox color="white" text="뒤로" navigation={navigation} />
        </FooterContainer>
      </ContentFooterContainer>
    </LayoutContainer>
  );
};

const DataContainer = styled(QRcodeContainer)`
  padding: 5%;
  align-items: flex-start;
`;
const DataText = styled(QRcodeInfoNameText)`
  padding-top: 2%;
  font-size: 23px;
  font-weight: bold;
`;
const DataTextDetail = styled(QRcodeInfoNameText)`
  padding-top: 2%;
  font-size: 15px;
  color: red;
`;

export default AuthBio;
