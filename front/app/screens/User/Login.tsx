import React, { useState } from "react";
import { Text, StyleSheet, Button, TouchableOpacity } from "react-native";
import styled from "styled-components/native";
import axios from "axios";
import AsyncStorage from "@react-native-async-storage/async-storage";
import IdController from "../../components/Controller/IdController";
import PasswordController from "../../components/Controller/PasswordController";
import { LayoutContainer, HeaderText } from "../styled";
import BtnBox from "../../components/BtnBox";

const ContentContainer = styled.View`
  flex: 1;
  justify-content: center;
  align-items: center;
`;

interface PropsType {
  setLogin: (a: boolean) => void;
  navigation: any;
}

const Login: React.FC<PropsType> = ({ setLogin, navigation: { navigate } }) => {
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");
  const url = "http://k6S101.p.ssafy.io:8080/user/login";

  const onSubmit = async () => {
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
        console.log(res);
        const accessToken = res.data.accessToken;
        const refreshToken = res.data.refreshToken;
        axios.defaults.headers.common["Authorization"] =
          "Bearer " + accessToken;
        AsyncStorage.setItem("accessToken", accessToken);
        AsyncStorage.setItem("refreshToken", refreshToken);
      })
      .catch((err: any) => {
        console.log(err);
      });
    setId("");
    setPassword("");
  };

  return (
    <LayoutContainer>
      <HeaderText>로그인</HeaderText>
      <ContentContainer>
        <IdController setId={setId} text="아이디" />
        <PasswordController setPassword={setPassword} text="비밀번호" />
        <BtnBox
          color="blue"
          text="로그인"
          setter={onSubmit}
          setLogin={setLogin}
        />

        <TouchableOpacity
          style={styles.textBtn}
          onPress={() => navigate("Register")}
        >
          <Text>회원이 아니신가요?</Text>
        </TouchableOpacity>
        <TouchableOpacity
          style={styles.textBtn}
          onPress={() => navigate("ForgetPassword")}
        >
          <Text>비밀번호를 잊으셨나요?</Text>
        </TouchableOpacity>
      </ContentContainer>
    </LayoutContainer>
  );
};

const styles = StyleSheet.create({
  input: {
    height: 40,
    width: 300,
    // backgroundColor: "#A23412",
    fontSize: 30,
    fontWeight: "200",
    borderColor: "black",
    borderStyle: "solid",
    borderWidth: 1,
  },
  textBtn: {},
});

export default Login;
