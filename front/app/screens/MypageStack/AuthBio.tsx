import BtnBox from "../../components/BtnBox";
import {
  ContentContainer,
  ContentFooterContainer,
  FooterContainer,
  HeaderText,
  LayoutContainer,
  QRcodeContainer,
  QRcodeInfoContainer,
  QRcodeInfoNameText,
} from "../styled";
import * as Application from "expo-application";
import * as Device from "expo-device";
import styled from "styled-components/native";
import { Alert } from "react-native";

// Component _ AuthBio
const AuthBio = ({ navigation }: any) => {
  // const
  // 안드로이드 앱 서명 키
  const androidId = Application.androidId;
  // 기기 모델 명
  const modelName = Device.modelName;
  // 애플 앱 서명 키
  const idData =
    androidId === null ? Application.getIosIdForVendorAsync() : androidId;
  // 데이터
  const data = { id: idData, model: modelName };

  // useState
  let temp = "";

  // Axios
  // 생체인증 정보 등록
  const registBio = () => {
    if (temp) {
      Alert.alert("알림", "이미 등록된 기기를 삭제하고 등록하시겠습니까?", [
        {
          text: "아니요",
          onPress: () => console.log("Cancel Pressed"),
          style: "cancel",
        },
        {
          text: "네",
          onPress: () => {
            console.log("생체인증 정보 등록", data);
          },
        },
      ]);
    } else {
      console.log("생체인증 정보 등록", data);
      Alert.alert("알림", `${data.model}을 등록하였습니다.`);
    }
  };

  return (
    <LayoutContainer>
      <HeaderText>생체인증 정보 등록</HeaderText>
      <ContentFooterContainer>
        <ContentContainer>
          <DataContainer>
            <DataText>현재 등록된 모델 : {temp ? data.model : "-"}</DataText>
            <DataTextDetail>
              {temp
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
