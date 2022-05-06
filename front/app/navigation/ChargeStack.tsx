import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { useEffect } from "react";
import { Text, TouchableOpacity, View } from "react-native";
import Charge from "../screens/Charge";

const Stack = createNativeStackNavigator();

interface PropsType {
  balance: string;
  navigation: any;
}

const Charging: React.FC<PropsType> = ({ navigation: { navigate } }) => {
  useEffect(() => {
    setTimeout(() => navigate?.("ChargeMain"), 2000);
  }, []);
  return (
    <View>
      <TouchableOpacity onPress={() => navigate?.("ChargeMain")}>
        <Text>충전중~</Text>
      </TouchableOpacity>
    </View>
  );
};

const ChargeStack: React.FC<PropsType> = ({ balance, navigation }) => {
  return (
    <Stack.Navigator
      screenOptions={{
        headerShown: false,
        contentStyle: { backgroundColor: "white" },
        animation: "slide_from_bottom",
      }}
    >
      <Stack.Screen name="ChargeMain">
        {(props) => (
          <Charge {...props} balance={balance} navigation={navigation} />
        )}
      </Stack.Screen>
      <Stack.Screen name="Charging" component={Charging} />
    </Stack.Navigator>
  );
};

export default ChargeStack;
