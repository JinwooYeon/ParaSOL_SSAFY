import React, { useState } from "react";
import { Text, StyleSheet, TouchableOpacity, Alert, View } from "react-native";
import styled from "styled-components/native";
import axios from "axios";
import AsyncStorage from "@react-native-async-storage/async-storage";
import IdController from "../../components/Controller/IdController";
import PasswordController from "../../components/Controller/PasswordController";
import { LayoutContainer, HeaderText, FooterContainer } from "../styled";
import BtnBox from "../../components/BtnBox";

const ContentContainer = styled.View`
  flex: 1;
  margin: 20px auto;
  width: 80%;
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
        if (res.data) {
          const accessToken = res.data.accessToken;
          const refreshToken = res.data.refreshToken;
          axios.defaults.headers.common["Authorization"] =
            "Bearer " + accessToken;
          AsyncStorage.setItem("accessToken", accessToken);
          AsyncStorage.setItem("refreshToken", refreshToken);
          setLogin(true);
        } else {
          Alert.alert("아이디와 비밀번호를 확인해주세요.");
        }
      })
      .catch((err: any) => {
        Alert.alert("에러가 발생했습니다. 잠시 후에 다시 시도해주세요.");
        console.log(err);
      });
    setId("");
    setPassword("");
  };

  return (
    <LayoutContainer>
      <HeaderText>로그인</HeaderText>
      <ContentContainer>
        <View style={{ marginBottom: 150 }}>
          <IdController setId={setId} text="아이디" value={id} />
          <PasswordController
            setPassword={setPassword}
            text="비밀번호"
            value={password}
          />
        </View>
        <FooterContainer>
          <BtnBox
            color="blue"
            text="로그인"
            setter={onSubmit}
            setLogin={setLogin}
          />
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

const styles = StyleSheet.create({
  textBtn: {
    fontSize: 18,
    color: "grey",
    marginVertical: 7,
    textAlign: "center",
  },
});

export default Login;
