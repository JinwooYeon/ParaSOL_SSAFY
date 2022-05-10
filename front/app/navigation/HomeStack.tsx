import { createNativeStackNavigator } from "@react-navigation/native-stack";
import Home from "../screens/HomeStack/Home";
import Transaction from "../screens/HomeStack/Transaction";

interface PropsType {
  balance: string;
  navigation: any;
}

const Stack = createNativeStackNavigator();

const HomeStack: React.FC<PropsType> = ({ balance }) => {
  return (
    <Stack.Navigator
      initialRouteName="HomeMain"
      screenOptions={{
        contentStyle: { backgroundColor: "white" },
        animation: "slide_from_right",
        headerShown: false,
      }}
    >
      <Stack.Screen name="MypageMain">
        {(props) => <Home {...props} balance={balance} />}
      </Stack.Screen>
      <Stack.Screen name="Transaction">
        {(props) => <Transaction {...props} balance={balance} />}
      </Stack.Screen>
    </Stack.Navigator>
  );
};

export default HomeStack;
