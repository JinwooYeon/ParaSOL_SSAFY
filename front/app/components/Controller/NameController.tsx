import React from "react";
import { View, TextInput } from "react-native";
import { TextInputController } from "./controllerStyled";

interface PropsType {
  setName: (a: any) => void;
  text: string;
  value: string;
}

const NameController: React.FC<PropsType> = ({ setName, text, value }) => {
  return (
    <View>
      <TextInputController
        onChangeText={setName}
        value={value}
        placeholder={text}
        keyboardType="visible-password"
      />
    </View>
  );
};

export default NameController;
