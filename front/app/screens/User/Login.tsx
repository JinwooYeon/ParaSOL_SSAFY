import { useState } from "react";
import { Text, StyleSheet, TouchableOpacity, Alert, View } from "react-native";
import styled from "styled-components/native";
import axios from "axios";
import AsyncStorage from "@react-native-async-storage/async-storage";
import IdController from "../../components/Controller/IdController";
import PasswordController from "../../components/Controller/PasswordController";
import { LayoutContainer, HeaderText, FooterContainer } from "../styled";
import BtnBox from "../../components/BtnBox";

interface PropsType {
  // 로그인 여부 set
  setLogin: (a: boolean) => void;
  // stack navigation
  navigation: any;
}

// Component _ Login
const Login: React.FC<PropsType> = ({ setLogin, navigation: { navigate } }) => {
  // const
  // Axios url
  const url = "/user/login";

  // useState
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");

  // Axios
  const onSubmit = async () => {
    // [Error] 빈 입력값
    if (!id || !password) {
      Alert.alert("아이디와 비밀번호를 입력해주세요.");
      console.log("아이디와 비밀번호를 입력해주세요.");
      return;
    }
    const data = {
      id: id,
      password: password,
    };
    await axios
      .post(url, data, {
        headers: {
          "Content-Type": "Application/json",
        },
      })
      .then((res: any) => {
        const accessToken = res.data.accessToken;
        const refreshToken = res.data.refreshToken;
        axios.defaults.headers.common["Authorization"] =
          "Bearer " + accessToken;
        AsyncStorage.setItem("accessToken", accessToken);
        AsyncStorage.setItem("refreshToken", refreshToken);
        setLogin(true);
      })
      .catch((err: any) => {
        // [Error] 잘못된 입력값
        if (err.response.status === 400 || err.response.status === 404) {
          Alert.alert("아이디와 비밀번호를 확인해주세요.");
          console.log("아이디와 비밀번호를 확인해주세요.");
          setId("");
          setPassword("");
        }
        // [Error] 통신 오류
        Alert.alert("에러가 발생했습니다. 잠시 후에 다시 시도해주세요.");
      });
  };

  return (
    <LayoutContainer>
      <HeaderText>로그인</HeaderText>
      <ContentContainer>
        <View style={{ marginBottom: 200 }}>
          <IdController setId={setId} text="아이디" value={id} />
          <PasswordController
            setPassword={setPassword}
            text="비밀번호"
            value={password}
          />
        </View>
        <FooterContainer>
          <BtnBox color="blue" text="로그인" setter={onSubmit} />
          <TouchableOpacity onPress={() => navigate("Register")}>
            <Text style={styles.textBtn}>회원이 아니신가요?</Text>
          </TouchableOpacity>
          <TouchableOpacity onPress={() => navigate("ForgetPassword")}>
            <Text style={styles.textBtn}>비밀번호를 잊으셨나요?</Text>
          </TouchableOpacity>
        </FooterContainer>
      </ContentContainer>
    </LayoutContainer>
  );
};

const ContentContainer = styled.View`
  flex: 1;
  margin: 30px auto;
  width: 80%;
`;

const styles = StyleSheet.create({
  textBtn: {
    fontSize: 18,
    color: "grey",
    marginVertical: 7,
    textAlign: "center",
  },
});

export default Login;
