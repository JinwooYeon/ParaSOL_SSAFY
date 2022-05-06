import React from "react";
import { View, TextInput } from "react-native";

interface PropsType {
  setBirth: (a: any) => void;
}

const BirthController: React.FC<PropsType> = ({ setBirth }) => {
  return (
    <View>
      <TextInput onChangeText={setBirth} placeholder="생년월일" />
    </View>
  );
};

export default BirthController;
