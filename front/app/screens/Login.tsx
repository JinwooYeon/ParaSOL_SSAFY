import React, { useState } from "react";
import { Text, TextInput, StyleSheet, Button, Alert } from "react-native";
import styled from "styled-components/native";
import axios from "axios";
import AsyncStorage from "@react-native-async-storage/async-storage";

const ContentContainer = styled.View`
  flex: 1;
  justify-content: center;
  align-items: center;
`;

interface PropsType {
  setLogin: (a: any) => void;
}

const Home: React.FC<PropsType> = ({ setLogin }) => {
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");
  const url = "http://k6S101.p.ssafy.io:8080/user/login";

  const onSubmit = async (id: string, password: string) => {
    const data = {
      id: id,
      password: password,
    };
    Alert.alert("hi");
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
    <ContentContainer>
      <Text>Login</Text>
      <TextInput
        style={styles.input}
        onChangeText={setId}
        value={id}
        placeholder="아이디"
      />
      <TextInput
        style={styles.input}
        onChangeText={setPassword}
        value={password}
        placeholder="비밀번호"
      />
      <Button title="Submit" onPress={() => onSubmit(id, password)}></Button>
      <Button title="LOGIN" onPress={onLoginTemp}></Button>
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
});

export default Home;
