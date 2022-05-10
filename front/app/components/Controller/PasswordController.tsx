import React from "react";
import { View } from "react-native";
import { TextInputController } from "./controllerStyled";

interface PropsType {
  setPassword: (a: any) => void;
  text: string;
  value: string;
}

const PasswordController: React.FC<PropsType> = ({
  setPassword,
  text,
  value,
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
