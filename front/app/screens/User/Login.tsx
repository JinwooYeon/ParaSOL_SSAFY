import React, { useState } from "react";
import { Text, StyleSheet, Button, TouchableOpacity } from "react-native";
import styled from "styled-components/native";
import axios from "axios";
import AsyncStorage from "@react-native-async-storage/async-storage";
import IdController from "../../components/Controller/IdController";
import PasswordController from "../../components/Controller/PasswordController";
import { LayoutContainer, HeaderText } from "../styled";

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

  const onSubmit = async (id: string, password: string) => {
    const data = {
      id: id,
      password: password,
    };
    console.log(data);
    await axios
      .post(url, data, {
        headers: {
          "Content-Type": "Application/json",
        },
      })
      .then((res: any) => {
        console.log(res);
        const token = res.data.token;
        axios.defaults.headers.common["Authorization"] = "Bearer " + token;
        AsyncStorage.setItem("token", token);
      })
      .catch((err: any) => {
        console.log(err);
      });
    setId("");
    setPassword("");
  };

  const onLoginTemp = () => {
    setLogin(true);
  };

  return (
    <LayoutContainer>
      <HeaderText>로그인</HeaderText>
      <ContentContainer>
        <IdController setId={setId} text="아이디" />
        <PasswordController setPassword={setPassword} text="비밀번호" />
        <Button title="Submit" onPress={() => onSubmit(id, password)}></Button>
        <Button title="LOGIN" onPress={onLoginTemp}></Button>

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
