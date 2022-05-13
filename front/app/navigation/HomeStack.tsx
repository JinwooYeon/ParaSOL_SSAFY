import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { useState } from "react";
import Home from "../screens/HomeStack/Home";
import Transaction from "../screens/HomeStack/Transaction";
import TransactionConfirm from "../screens/HomeStack/TransactionConfirm";

const Stack = createNativeStackNavigator();

interface PropsType {
  // 잔액
  balance: string;
  // stack navigation
  navigation: any;
}

// Component _ HomeStack
const HomeStack: React.FC<PropsType> = ({ balance }) => {
  // useState
  // 송금할 주소
  const [info, setInfo] = useState<string>("");
  // 송금할 금액
  const [price, setPrice] = useState<string>("0");

  return (
    <Stack.Navigator
      initialRouteName="HomeMain"
      screenOptions={{
        contentStyle: { backgroundColor: "white" },
        animation: "slide_from_right",
        headerShown: false,
      }}
    >
      {/* 홈 첫 스크린 */}
      <Stack.Screen name="HomeMain">
        {(props) => <Home {...props} balance={balance} />}
      </Stack.Screen>
      {/* 송금 */}
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
      {/* 송금 확인 */}
      <Stack.Screen name="TransactionConfirm">
        {(props) => <TransactionConfirm {...props} info={info} price={price} />}
      </Stack.Screen>
    </Stack.Navigator>
  );
};

export default HomeStack;
