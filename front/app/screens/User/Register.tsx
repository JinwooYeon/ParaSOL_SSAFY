import React, { useState } from "react";
import { Text, Button } from "react-native";
import styled from "styled-components/native";
import IdController from "../../components/Controller/IdController";
import PasswordController from "../../components/Controller/PasswordController";
import PasswordConfirmController from "../../components/Controller/PasswordConfirmController";
import NameController from "../../components/Controller/NameController";
import BirthController from "../../components/Controller/BirthController";
import { LayoutContainer, HeaderText } from "../styled";
import axios from "axios";
import BtnBox from "../../components/BtnBox";

const ContentContainer = styled.View`
  flex: 1;
  justify-content: center;
  align-items: center;
`;

interface PropsType {
  navigation: any;
}

const Register: React.FC<PropsType> = ({ navigation }) => {
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");
  const [passwordConfirm, setPasswordConfirm] = useState("");
  const [name, setName] = useState("");
  const url = "http://k6S101.p.ssafy.io:8080/user/register";

  const onSubmit = async () => {
    const data = {
      id: id,
      password: password,
      passwordConfirm: passwordConfirm,
      name: name,
    };
    console.log(data);

    await axios
      .post(url, data)
      .then((res) => {
        console.log(res);
        navigation.navigate("Login");
      })
      .catch((err) => {
        console.log(err);
      });

    setId("");
    setPassword("");
    setPasswordConfirm("");
    setName("");
  };

  return (
    <LayoutContainer>
      <HeaderText>회원가입</HeaderText>
      <ContentContainer>
        <Text>Register</Text>
        <IdController setId={setId} text="아이디" />
        <PasswordController setPassword={setPassword} text="비밀번호" />
        <PasswordConfirmController setPasswordConfirm={setPasswordConfirm} />
        <NameController setName={setName} text="이름" />
        <BtnBox
          color="white"
          text="회원가입"
          setter={onSubmit}
          navigation={navigation}
        ></BtnBox>
        <BtnBox color="white" text="뒤로" navigation={navigation}></BtnBox>
      </ContentContainer>
    </LayoutContainer>
  );
};

export default Register;
