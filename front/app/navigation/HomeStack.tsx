import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { useState } from "react";
import Home from "../screens/HomeStack/Home";
import Transaction from "../screens/HomeStack/Transaction";
import TransactionConfirm from "../screens/HomeStack/TransactionConfirm";

interface PropsType {
  balance: string;
  navigation: any;
}

const Stack = createNativeStackNavigator();

const HomeStack: React.FC<PropsType> = ({ balance }) => {
  const [info, setInfo] = useState("");
  const [price, setPrice] = useState("0");

  return (
    <Stack.Navigator
      initialRouteName="HomeMain"
      screenOptions={{
        contentStyle: { backgroundColor: "white" },
        animation: "slide_from_right",
        headerShown: false,
      }}
    >
      <Stack.Screen name="HomeMain">
        {(props) => <Home {...props} balance={balance} />}
      </Stack.Screen>
      <Stack.Screen name="Transaction">
        {(props) => (
          <Transaction
            {...props}
            balance={balance}
            info={info}
            setInfo={setInfo}
            price={price}
            setPrice={setPrice}
          />
        )}
      </Stack.Screen>
      <Stack.Screen name="TransactionConfirm">
        {(props) => <TransactionConfirm {...props} info={info} price={price} />}
      </Stack.Screen>
    </Stack.Navigator>
  );
};

export default HomeStack;
