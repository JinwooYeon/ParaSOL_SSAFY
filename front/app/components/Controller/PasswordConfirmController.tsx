import React from "react";
import { View, TextInput } from "react-native";
import { TextInputController } from "./controllerStyled";

interface PropsType {
  setPasswordConfirm: (a: any) => void;
  value: string;
}

const PasswordConfirmController: React.FC<PropsType> = ({
  setPasswordConfirm,
  value,
}) => {
  return (
    <View>
      <TextInputController
        onChangeText={setPasswordConfirm}
        secureTextEntry={true}
        value={value}
        placeholder="비밀번호 확인"
      />
    </View>
  );
};

export default PasswordConfirmController;
