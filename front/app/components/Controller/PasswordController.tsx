import React from "react";
import { View, TextInput } from "react-native";

interface PropsType {
  setPassword: (a: any) => void;
}

const PasswordController: React.FC<PropsType> = ({ setPassword }) => {
  return (
    <View>
      <TextInput
        onChangeText={setPassword}
        secureTextEntry={true}
        placeholder="비밀번호"
      />
    </View>
  );
};

export default PasswordController;
