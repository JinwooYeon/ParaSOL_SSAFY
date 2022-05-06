import React from "react";
import { Text, Button } from "react-native";
import styled from "styled-components/native";

const ContentContainer = styled.View`
  flex: 1;
  justify-content: center;
  align-items: center;
`;

interface PropsType {
  navigation: any;
}

const ForgetPassword: React.FC<PropsType> = ({ navigation: { goBack } }) => {
  return (
    <ContentContainer>
      <Text>ForgetPassword</Text>

      <Button title="Submit"></Button>
      <Button onPress={() => goBack()} title="뒤로" />
    </ContentContainer>
  );
};

export default ForgetPassword;
