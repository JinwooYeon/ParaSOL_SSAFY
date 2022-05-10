import React from "react";
import { View, TextInput } from "react-native";

interface PropsType {
  setName: (a: any) => void;
  text: string;
}

const NameController: React.FC<PropsType> = ({ setName, text }) => {
  return (
    <View>
      <TextInput onChangeText={setName} placeholder={text} />
    </View>
  );
};

export default NameController;
