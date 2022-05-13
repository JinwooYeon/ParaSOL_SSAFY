import { useState } from "react";
import { RefreshControl, ScrollView, View } from "react-native";
import {
  Balance,
  BoxContainer,
  BalanceTextContainer,
  HeaderText,
} from "../screens/styled";

interface PropsType {
  // 거래내역 or 페이 or 홈
  category: string;
  // 잔액
  num: string;
}

// Component _ BalanceBox
const BalanceBox: React.FC<PropsType> = ({ category, num }) => {
  // useState
  // 리프레쉬
  const [refreshing, setRefreshing] = useState(false);

  // method
  const onRefresh = () => {
    setRefreshing(true);
    setTimeout(() => setRefreshing(false), 2000);
  };

  return (
    <ScrollView
      refreshControl={
        <RefreshControl refreshing={refreshing} onRefresh={onRefresh} />
      }
      scrollEnabled={false}
    >
      <View>
        <HeaderText>{category}</HeaderText>
        <BoxContainer>
          <Balance>잔액</Balance>
          <BalanceTextContainer>
            <Balance>{num}</Balance>
            <Balance>원</Balance>
          </BalanceTextContainer>
        </BoxContainer>
      </View>
    </ScrollView>
  );
};

export default BalanceBox;
