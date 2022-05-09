import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { useEffect } from "react";
import { Text, TouchableOpacity, View } from "react-native";
import Pay from "../screens/Pay";

const Stack = createNativeStackNavigator();

interface PropsType {
  balance: string;
  navigation: any;
}

const Charging: React.FC<PropsType> = ({ navigation: { navigate } }) => {
  useEffect(() => {
    setTimeout(() => navigate?.("PayMain"), 2000);
  }, []);
  return (
    <View>
      <TouchableOpacity onPress={() => navigate?.("PayMain")}>
        <Text>충전중~</Text>
      </TouchableOpacity>
    </View>
  );
};
const Withdrawing: React.FC<PropsType> = ({ navigation: { navigate } }) => {
  useEffect(() => {
    setTimeout(() => navigate?.("PayMain"), 2000);
  }, []);
  return (
    <View>
      <TouchableOpacity onPress={() => navigate?.("PayMain")}>
        <Text>출금중~</Text>
      </TouchableOpacity>
    </View>
  );
};

const PayStack: React.FC<PropsType> = ({ balance, navigation }) => {
  return (
    <Stack.Navigator
      screenOptions={{
        headerShown: false,
        contentStyle: { backgroundColor: "white" },
        animation: "slide_from_bottom",
      }}
    >
      <Stack.Screen name="PayMain">
        {(props) => (
          <Pay {...props} balance={balance} navigation={navigation} />
        )}
      </Stack.Screen>
      <Stack.Screen name="Charging" component={Charging} />
      <Stack.Screen name="Withdrawing" component={Withdrawing} />
    </Stack.Navigator>
  );
};

export default PayStack;
