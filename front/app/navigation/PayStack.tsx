import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { useEffect, useState } from "react";
import { Text, TouchableOpacity, View } from "react-native";
import ConnectedAccountBox from "../components/ConnectedAccountBox";
import Pay from "../screens/Pay";
import { WaitingContainer } from "../screens/styled";

const Stack = createNativeStackNavigator();

interface PropsType {
  balance: string;
  bankInfo: any;
  navigation: any;
}
interface WaitingPropsType {
  method: boolean;
  bankInfo: any;
  price: string;
  navigation: any;
}

const Waiting: React.FC<WaitingPropsType> = ({
  navigation: { navigate },
  method,
  price,
  bankInfo,
}) => {
  return (
    <WaitingContainer>
      <Text>
        {bankInfo.bankName} / {bankInfo.bankNum} 으로
      </Text>
      {method ? (
        <Text>{price}원을 충전하시겠습니까?</Text>
      ) : (
        <Text>{price}원을 출금하시겠습니까?</Text>
      )}
      <TouchableOpacity onPress={() => navigate?.("PayMain")}>
        <Text>확인</Text>
      </TouchableOpacity>
    </WaitingContainer>
  );
};

const PayStack: React.FC<PropsType> = ({ balance, bankInfo, navigation }) => {
  const [method, setMethod] = useState(false);
  const [price, setPrice] = useState("0");

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
          <Pay
            {...props}
            balance={balance}
            bankInfo={bankInfo}
            price={price}
            setPrice={setPrice}
            navigation={navigation}
            setMethod={setMethod}
          />
        )}
      </Stack.Screen>
      <Stack.Screen name="Waiting">
        {(props) => (
          <Waiting
            {...props}
            method={method}
            bankInfo={bankInfo}
            price={price}
          />
        )}
      </Stack.Screen>
    </Stack.Navigator>
  );
};

export default PayStack;
