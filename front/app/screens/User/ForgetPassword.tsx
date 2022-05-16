import axios from "axios";
import { useState } from "react";
import { Alert, View } from "react-native";
import styled from "styled-components/native";
import BtnBox from "../../components/BtnBox";
import IdController from "../../components/Controller/IdController";
import NameController from "../../components/Controller/NameController";
import { FooterContainer, HeaderText, LayoutContainer } from "../styled";

interface PropsType {
  navigation: any;
}

// Component _ ForgetPassword
const ForgetPassword: React.FC<PropsType> = ({ navigation }) => {
  // const
  // Axios url
  const url = "http://k6S101.p.ssafy.io:8080/client/password";

  // useState
  const [id, setId] = useState("");
  const [name, setName] = useState("");

  // Axios
  const getNewPassword = async () => {
    // [Error] 빈 입력값
    if (!id) {
      Alert.alert("아이디를 입력해주세요.");
      console.log("아이디를 입력해주세요.");
      return;
    }
    if (!name) {
      Alert.alert("이름을 입력해주세요.");
      console.log("이름을 입력해주세요.");
      return;
    }
    const data = {
      id: id,
      name: name,
    };
    await axios
      .post(url, data)
      .then((res) => {
        Alert.alert(
          "비밀번호 재설정 성공! 변경된 비밀번호로 다시 로그인해주세요."
        );
        navigation.navigate("Login");
      })
      .catch((err) => {
        Alert.alert(
          "비밀번호 재설정에 실패했습니다. 입력값을 다시 한번 확인해주세요."
        );

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

const ContentContainer = styled.View`
  flex: 1;
  margin: 30px auto;
  width: 80%;
`;

export default ForgetPassword;
