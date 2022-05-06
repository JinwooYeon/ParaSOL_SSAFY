import { useState } from "react";
import { RefreshControl, ScrollView, View } from "react-native";
import {
  Balance,
  BoxContainer,
  BalanceTextContainer,
  HeaderText,
} from "../screens/styled";

interface PropsType {
  category: string;
  num: string;
}

const BalanceBox: React.FC<PropsType> = ({ category, num }) => {
  const [refreshing, setRefreshing] = useState(false);

  const onRefresh = () => {
    setRefreshing(true);
    setTimeout(() => setRefreshing(false), 2000);
  };
  return (
    // <ScrollView
    //   refreshControl={
    //     <RefreshControl refreshing={refreshing} onRefresh={onRefresh} />
    //   }
    //   scrollEnabled={false}
    // >
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
    // </ScrollView>
  );
};

export default BalanceBox;
