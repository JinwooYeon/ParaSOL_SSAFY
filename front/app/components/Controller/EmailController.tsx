import React from "react";
import { View, TextInput } from "react-native";

interface PropsType {
  setEmail: (a: any) => void;
}

const EmailController: React.FC<PropsType> = ({ setEmail }) => {
  return (
    <View>
      <TextInput onChangeText={setEmail} placeholder="이메일" />
    </View>
  );
};

export default EmailController;
