import { Alert, Text } from "react-native";
import BtnBox from "../../components/BtnBox";
import styled from "styled-components/native";
import {
  ContentFooterContainer,
  FooterContainer,
  HeaderText,
  LayoutContainer,
} from "../styled";
import axios from "axios";
import AsyncStorage from "@react-native-async-storage/async-storage";

interface PropsType {
  navigation: any;
  setLogin: (a: boolean) => void;
}

// Component _ Delete
const Delete: React.FC<PropsType> = ({ navigation, setLogin }) => {
  // const
  // Axios 새로운 인증 토큰 url
  const tokenUrl = "http://k6S101.p.ssafy.io:8080/user/token";
  // Axios 회원 탈퇴 url
  const delUrl = "http://k6s101.p.ssafy.io:8080/user";

  // Axios
  // 새로운 인증 토큰 발급
  const getNewToken = async (): Promise<any> => {
    const refreshToken = await AsyncStorage.getItem("refreshToken");
    await axios({
      method: "get",
      url: tokenUrl,
      headers: { Authorization: `Bearer ${refreshToken}` },
      params: refreshToken,
    })
      .then((response) => {
        console.log(response.data);
        AsyncStorage.setItem("accessToken", response.data.accessToken);
        return true;
      })
      .catch((err) => {
        console.log(err);
        Alert.alert("토큰 만료! 다시 로그인해주세요.");
        AsyncStorage.clear();
        setLogin(false);
        return false;
      });
  };
  // 회원 탈퇴
  const userDel = async () => {
    console.log("del");
    const accessToken = await AsyncStorage.getItem("accessToken");
    await axios({
      method: "delete",
      url: delUrl,
      headers: { Authorization: `Bearer ${accessToken}` },
    })
      .then((res) => {
        console.log(res.data);
        console.log("회원 탈퇴 성공!");
        setLogin(false);
        AsyncStorage.removeItem("accessToken");
        AsyncStorage.removeItem("refreshToken");
      })
      .catch(async (err) => {
        console.log(err);
        if (err.response.status === 401 && (await getNewToken?.())) {
          userDel();
        } else {
          Alert.alert("회원 탈퇴 실패.");
        }
      });
  };

  return (
    <LayoutContainer>
      <HeaderText>회원탈퇴</HeaderText>
      <ContentFooterContainer>
        <ContentContainer>
          <Text style={{ fontSize: 20 }}>정말 탈퇴하시겠습니까?</Text>
          <Text style={{ fontSize: 15 }}>
            삭제된 정보는 되돌릴 수 없습니다.
          </Text>
        </ContentContainer>
        <FooterContainer>
          <BtnBox
            color="red"
            text="회원 탈퇴"
            navigation={navigation}
            setter={userDel}
          />
          <BtnBox color="white" text="뒤로" navigation={navigation} />
        </FooterContainer>
      </ContentFooterContainer>
    </LayoutContainer>
  );
};

const ContentContainer = styled.View`
  flex: 1;
  justify-content: center;
  align-items: center;
  margin-bottom: 90px;
  text-align: center;
`;

export default Delete;
