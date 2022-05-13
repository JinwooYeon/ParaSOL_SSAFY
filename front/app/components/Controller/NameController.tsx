import React from "react";
import { View } from "react-native";
import { TextInputController } from "./controllerStyled";

interface PropsType {
  // 이름 placeholder
  text: string;
  // 이름 입력 데이터
  value: string;
  // 이름 입력 데이터 set
  setName: (a: any) => void;
}

// Component _ NameController
const NameController: React.FC<PropsType> = ({ text, value, setName }) => {
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
