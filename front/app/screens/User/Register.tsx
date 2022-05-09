import React, { useState } from "react";
import { Text, Button } from "react-native";
import styled from "styled-components/native";
import IdController from "../../components/Controller/IdController";
import PasswordController from "../../components/Controller/PasswordController";
import PasswordConfirmController from "../../components/Controller/PasswordConfirmController";
import EmailController from "../../components/Controller/EmailController";
import BirthController from "../../components/Controller/BirthController";
import { LayoutContainer, HeaderText } from "../styled";

const ContentContainer = styled.View`
  flex: 1;
  justify-content: center;
  align-items: center;
`;

interface PropsType {
  navigation: any;
}

const Register: React.FC<PropsType> = ({ navigation: { goBack } }) => {
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");
  const [passwordConfirm, setPasswordConfirm] = useState("");
  const [email, setEmail] = useState("");
  const [birth, setBirth] = useState("");

  const onSubmit = async () => {
    const data = {
      id: id,
      password: password,
      passwordConfirm: passwordConfirm,
      email: email,
      birth: birth,
    };
    console.log(data);

    setId("");
    setPassword("");
    setPasswordConfirm("");
    setEmail("");
    setBirth("");
  };

  return (
    <LayoutContainer>
      <HeaderText>회원가입</HeaderText>
      <ContentContainer>
        <Text>Register</Text>
        <IdController setId={setId} />
        <PasswordController setPassword={setPassword} />
        <PasswordConfirmController setPasswordConfirm={setPasswordConfirm} />
        <EmailController setEmail={setEmail} />
        <BirthController setBirth={setBirth} />
        <Button title="Submit" onPress={() => onSubmit()}></Button>
        <Button onPress={() => goBack()} title="뒤로" />
      </ContentContainer>
    </LayoutContainer>
  );
};

export default Register;
