import React from "react";
import { View, TextInput } from "react-native";

interface PropsType {
  setPasswordConfirm: (a: any) => void;
}

const PasswordConfirmController: React.FC<PropsType> = ({
  setPasswordConfirm,
}) => {
  return (
    <View>
      <TextInput
        onChangeText={setPasswordConfirm}
        secureTextEntry={true}
        placeholder="비밀번호 확인"
      />
    </View>
  );
};

export default PasswordConfirmController;
