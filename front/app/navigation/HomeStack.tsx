import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { useState } from "react";
import Home from "../screens/HomeStack/Home";
import Scanner from "../screens/HomeStack/Scanner";
import Transaction from "../screens/HomeStack/Transaction";
import TransactionConfirm from "../screens/HomeStack/TransactionConfirm";

const Stack = createNativeStackNavigator();

interface PropsType {
  // 잔액
  balance: string;
  // 아이디
  id: string;
  // 잔액 set
  setBalance: (a: string) => void;
  // 새로운 인증 토큰 발급
  getNewToken: () => Promise<any>;
  // 2차 인증 정보 등록 여부
  auth: any;
}

// Component _ HomeStack
const HomeStack: React.FC<PropsType> = ({
  balance,
  id,
  setBalance,
  getNewToken,
  auth,
}) => {
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
        {(props) => (
          <Home
            {...props}
            balance={balance}
            id={id}
            setBalance={setBalance}
            getNewToken={getNewToken}
          />
        )}
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
            setBalance={setBalance}
            getNewToken={getNewToken}
          />
        )}
      </Stack.Screen>
      {/* 송금 확인 */}
      <Stack.Screen name="TransactionConfirm">
        {(props) => (
          <TransactionConfirm
            {...props}
            info={info}
            price={price}
            getNewToken={getNewToken}
            auth={auth}
            setBalance={setBalance}
          />
        )}
      </Stack.Screen>
      {/* 스캐너 */}
      <Stack.Screen name="Scanner">
        {(props) => (
          <Scanner
            {...props}
            balance={balance}
            setInfo={setInfo}
            setBalance={setBalance}
            getNewToken={getNewToken}
          />
        )}
      </Stack.Screen>
    </Stack.Navigator>
  );
};

export default HomeStack;
