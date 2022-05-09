import React from "react";
import { View, TextInput } from "react-native";

interface PropsType {
  setName: (a: any) => void;
}

const NameController: React.FC<PropsType> = ({ setName }) => {
  return (
    <View>
      <TextInput onChangeText={setName} placeholder="이름" />
    </View>
  );
};

export default NameController;
