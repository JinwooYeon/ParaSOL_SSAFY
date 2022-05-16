import React from "react";
import { View } from "react-native";
import { TextInputController } from "./controllerStyled";

interface PropsType {
  // 비밀번호 placeholder
  text: string;
  // 비밀번호 입력 데이터
  value: string;
  // 비밀번호 입력 데이터 set
  setPassword: (a: any) => void;
}

// Component _ PasswordController
const PasswordController: React.FC<PropsType> = ({
  text,
  value,
  setPassword,
}) => {
  return (
    <View>
      <TextInputController
        onChangeText={setPassword}
        secureTextEntry={true}
        value={value}
        placeholder={text}
      />
    </View>
  );
};

export default PasswordController;
