import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { useEffect } from "react";
import { Text, TouchableOpacity, View } from "react-native";
import Withdraw from "../screens/Withdraw";

const Stack = createNativeStackNavigator();

interface PropsType {
  balance: string;
  navigation: any;
}

const Withdrawing: React.FC<PropsType> = ({ navigation: { navigate } }) => {
  useEffect(() => {
    setTimeout(() => navigate?.("WithdrawMain"), 2000);
  }, []);
  return (
    <View>
      <TouchableOpacity onPress={() => navigate?.("WithdrawMain")}>
        <Text>출금중~</Text>
      </TouchableOpacity>
    </View>
  );
};

const WithdrawStack: React.FC<PropsType> = ({ balance, navigation }) => {
  return (
    <Stack.Navigator
      screenOptions={{
        headerShown: false,
        contentStyle: { backgroundColor: "white" },
        animation: "slide_from_bottom",
      }}
    >
      <Stack.Screen name="WithdrawMain">
        {(props) => (
          <Withdraw {...props} balance={balance} navigation={navigation} />
        )}
      </Stack.Screen>
      <Stack.Screen name="Withdrawing" component={Withdrawing} />
    </Stack.Navigator>
  );
};

export default WithdrawStack;
