import React from "react";
import { View, TextInput } from "react-native";

interface PropsType {
  setPassword: (a: any) => void;
  text: string;
}

const PasswordController: React.FC<PropsType> = ({ setPassword, text }) => {
  return (
    <View>
      <TextInput
        onChangeText={setPassword}
        secureTextEntry={true}
        placeholder={text}
      />
    </View>
  );
};

export default PasswordController;
