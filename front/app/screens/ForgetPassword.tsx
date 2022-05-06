import React from "react";
import { Text, Button } from "react-native";
import styled from "styled-components/native";

const ContentContainer = styled.View`
  flex: 1;
  justify-content: center;
  align-items: center;
`;

const ForgetPassword: React.FC = () => {
  return (
    <ContentContainer>
      <Text>ForgetPassword</Text>

      <Button title="Submit"></Button>
    </ContentContainer>
  );
};

export default ForgetPassword;
