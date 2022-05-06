import React from "react";
import { View, TextInput } from "react-native";

interface PropsType {
  setId: (a: any) => void;
}

const IdController: React.FC<PropsType> = ({ setId }) => {
  return (
    <View>
      <TextInput onChangeText={setId} placeholder="아이디" />
    </View>
  );
};

export default IdController;
