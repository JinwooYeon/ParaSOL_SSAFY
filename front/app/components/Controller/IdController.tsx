import React from "react";
import { View } from "react-native";
import { TextInputController } from "./controllerStyled";

interface PropsType {
  // 아이디 placeholder
  text: string;
  // 아이디 입력 데이터
  value: string;
  // 아이디 데이터 set
  setId: (a: any) => void;
}

// Component _ IdController
const IdController: React.FC<PropsType> = ({ text, value, setId }) => {
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
