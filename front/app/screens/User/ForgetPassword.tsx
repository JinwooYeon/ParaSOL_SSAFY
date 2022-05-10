import axios from "axios";
import React, { useState } from "react";
import { Text, Button } from "react-native";
import styled from "styled-components/native";
import BtnBox from "../../components/BtnBox";
import IdController from "../../components/Controller/IdController";
import NameController from "../../components/Controller/NameController";
import PasswordController from "../../components/Controller/PasswordController";

const ContentContainer = styled.View`
  flex: 1;
  justify-content: center;
  align-items: center;
`;

interface PropsType {
  navigation: any;
}

const ForgetPassword: React.FC<PropsType> = ({ navigation }) => {
  const [id, setId] = useState("");
  const [name, setName] = useState("");
  const url = "http://k6s101.p.ssafy.io:8080/client/password";

  const getNewPassword = async () => {
    const data = {
      id: id,
      name: name,
    };
    await axios
      .post(url, data)
      .then((res) => {
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <ContentContainer>
      <Text>ForgetPassword</Text>
      <IdController setId={setId} text="아이디" />
      <NameController setName={setName} text="이름" />
      <BtnBox color="blue" text="비밀번호 재발급" setter={getNewPassword} />
      <BtnBox color="white" text="뒤로" navigation={navigation} />
    </ContentContainer>
  );
};

export default ForgetPassword;
