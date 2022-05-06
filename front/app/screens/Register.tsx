import React, { useState } from "react";
import {
  Text,
  TextInput,
  StyleSheet,
  Button,
  Alert,
  TouchableOpacity,
} from "react-native";
import styled from "styled-components/native";
import axios from "axios";
import AsyncStorage from "@react-native-async-storage/async-storage";
import BtnBox from "../components/BtnBox";
import IdController from "../components/Controller/IdController";
import PasswordController from "../components/Controller/PasswordController";
import PasswordConfirmController from "../components/Controller/PasswordConfirmController";
import EmailController from "../components/Controller/EmailController";
import BirthController from "../components/Controller/BirthController";

const ContentContainer = styled.View`
  flex: 1;
  justify-content: center;
  align-items: center;
`;

const Register: React.FC = () => {
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
    <ContentContainer>
      <Text>Register</Text>
      <IdController setId={setId} />
      <PasswordController setPassword={setPassword} />
      <PasswordConfirmController setPasswordConfirm={setPasswordConfirm} />
      <EmailController setEmail={setEmail} />
      <BirthController setBirth={setBirth} />
      <Button title="Submit" onPress={() => onSubmit()}></Button>
    </ContentContainer>
  );
};

export default Register;
