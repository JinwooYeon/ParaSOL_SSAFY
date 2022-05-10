import React from "react";
import { View, TextInput } from "react-native";

interface PropsType {
  setId: (a: any) => void;
  text: string;
}

const IdController: React.FC<PropsType> = ({ setId, text }) => {
  return (
    <View>
      <TextInput onChangeText={setId} placeholder={text} />
    </View>
  );
};

export default IdController;
