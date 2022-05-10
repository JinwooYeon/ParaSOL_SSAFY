import React from "react";
import { View } from "react-native";
import { TextInputController } from "./controllerStyled";

interface PropsType {
  setId: (a: any) => void;
  text: string;
  value: string;
}

const IdController: React.FC<PropsType> = ({ setId, text, value }) => {
  return (
    <View>
      <TextInputController
        onChangeText={setId}
        value={value}
        placeholder={text}
        keyboardType="visible-password"
      />
    </View>
  );
};

export default IdController;
