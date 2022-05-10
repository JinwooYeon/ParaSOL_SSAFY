import axios from "axios";
import React, { useState } from "react";
import { View } from "react-native";
import styled from "styled-components/native";
import BtnBox from "../../components/BtnBox";
import IdController from "../../components/Controller/IdController";
import NameController from "../../components/Controller/NameController";
import { FooterContainer, HeaderText, LayoutContainer } from "../styled";

const ContentContainer = styled.View`
  flex: 1;
  margin: 30px auto;
  width: 80%;
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
    <LayoutContainer>
      <HeaderText>비밀번호 재설정</HeaderText>
      <ContentContainer>
        <View style={{ marginBottom: 200 }}>
          <IdController setId={setId} text="아이디" value={id} />
          <NameController setName={setName} text="이름" value={name} />
        </View>
        <FooterContainer>
          <BtnBox color="blue" text="비밀번호 재설정" setter={getNewPassword} />
          <BtnBox color="white" text="뒤로" navigation={navigation} />
        </FooterContainer>
      </ContentContainer>
    </LayoutContainer>
  );
};

export default ForgetPassword;
