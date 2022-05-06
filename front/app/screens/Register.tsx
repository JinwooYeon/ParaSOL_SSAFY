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

interface PropsType {
  setLogin: (a: any) => void;
  navigation: any;
}

const Register: React.FC<PropsType> = ({
  setLogin,
  navigation: { navigate },
}) => {
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");
  const [passwordConfirm, setPasswordConfirm] = useState("");
  const [email, setEmail] = useState("");
  const [birth, setBirth] = useState("");
  const url = "http://k6S101.p.ssafy.io:8080/user/Register";

  const onSubmit = async (id: string, password: string) => {
    const data = {
      id: id,
      password: password,
      passwordConfirm: passwordConfirm,
      email: email,
      birth: birth,
    };
    console.log(data);
    // await axios
    //   .post(url, data, {
    //     headers: {
    //       "Content-Type": "Application/json",
    //     },
    //   })
    //   .then((res: any) => {
    //     console.log(res);
    //     const token = res.data.token;
    //     axios.defaults.headers.common["Authorization"] = "Bearer " + token;
    //     AsyncStorage.setItem("token", token);
    //   })
    //   .catch((err: any) => {
    //     console.log(err);
    //   });
    setId("");
    setPassword("");
  };

  const onLoginTemp = () => {
    setLogin(true);
  };

  return (
    <ContentContainer>
      <Text>Register</Text>
      <IdController setId={setId} />
      <PasswordController setPassword={setPassword} />
      <PasswordConfirmController setPasswordConfirm={setPasswordConfirm} />
      <EmailController setEmail={setEmail} />
      <BirthController setBirth={setBirth} />
      <Button title="Submit" onPress={() => onSubmit(id, password)}></Button>
      <Button title="LOGIN" onPress={onLoginTemp}></Button>

      <TouchableOpacity
        style={styles.textBtn}
        onPress={() => navigate("Register")}
      >
        <Text>회원이 아니신가요?</Text>
      </TouchableOpacity>
    </ContentContainer>
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

export default Register;
