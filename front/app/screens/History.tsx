import BalanceBox from "../components/BalanceBox";
import { HeaderContainer, LayoutContainer } from "./styled";
import {
  FlatList,
  SafeAreaView,
  StyleSheet,
  Text,
  TouchableOpacity,
  View,
} from "react-native";
import { useState } from "react";
import styled from "styled-components/native";

interface PropsType {
  // 잔액
  balance: string;
}
interface ItemPropsType {
  // 거래내역 정보
  item: any;
}

// Component _ Item
const Item: React.FC<ItemPropsType> = ({ item }) => (
  <TouchableOpacity style={styles.item}>
    <View
      style={{
        flexDirection: "row",
        alignItems: "center",
        justifyContent: "space-between",
        marginVertical: 5,
      }}
    >
      <View>
        <Text style={styles.title}>{item.title}</Text>
        {item.price[0] === "-" ? (
          <Text style={{ ...styles.title, fontSize: 16, color: "blue" }}>
            {item.price}원
          </Text>
        ) : (
          <Text style={{ ...styles.title, fontSize: 16, color: "red" }}>
            {item.price}원
          </Text>
        )}
      </View>
      <Text style={{ ...styles.title, fontSize: 13, color: "grey" }}>
        {item.id}
      </Text>
    </View>
  </TouchableOpacity>
);

// Component _ History
const History: React.FC<PropsType> = ({ balance }) => {
  // useState
  // 리프레쉬
  const [refreshing, setRefreshing] = useState<boolean>(false);
  // 총 거래 금액
  const [total, setTotal] = useState<string>("-1,234,000");
  // 거래내역 데이터
  const [data, setData] = useState([
    {
      // 날짜 데이터 (기본키)
      id: "05월 10일 12시 10분",
      // 사용처
      title: "연어를 덮밥 서초점",
      // 금액
      price: "-25,000",
    },
    {
      id: "05월 9일 12시 30분",
      title: "버거퀸 교대점",
      price: "+5,900",
    },
    {
      id: "05월 8일 18시 10분",
      title: "고도리 곱돌이탕 강남점",
      price: "+34,000",
    },
    {
      id: "05월 7일 12시 10분",
      title: "연어를 덮밥 서초점",
      price: "-25,000",
    },
    {
      id: "05월 6일 12시 10분",
      title: "버거퀸 교대점",
      price: "-5,900",
    },
    {
      id: "05월 5일 12시 10분",
      title: "고도리 곱돌이탕 강남점",
      price: "-34,000",
    },
    {
      id: "05월 4일 12시 10분",
      title: "연어를 덮밥 서초점",
      price: "+25,000",
    },
    {
      id: "05월 3일 12시 10분",
      title: "버거퀸 교대점",
      price: "-5,900",
    },
    {
      id: "05월 2일 12시 10분",
      title: "고도리 곱돌이탕 강남점",
      price: "+34,000",
    },
  ]);

  // method
  const renderItem = ({ item }: any) => {
    return <Item item={item} />;
  };
  const onRefresh = () => {
    setRefreshing(true);
    setTimeout(() => {
      setRefreshing(false);
    }, 2000);
  };

  return (
    <LayoutContainer>
      <HistoryHeaderContainer>
        <BalanceBox category="거래 내역" num={balance} />
      </HistoryHeaderContainer>
      <View
        style={{
          flexDirection: "row",
          justifyContent: "space-between",
          marginVertical: "5%",
          alignItems: "center",
        }}
      >
        <Text style={{ fontSize: 25, fontWeight: "bold" }}>5월</Text>
        {total[0] === "-" ? (
          <Text style={{ color: "blue" }}>{total}원</Text>
        ) : (
          <Text style={{ color: "red" }}>{total}원</Text>
        )}
      </View>
      <SafeAreaView style={styles.container}>
        <FlatList
          data={data}
          renderItem={renderItem}
          keyExtractor={(item) => item.id}
          onRefresh={onRefresh}
          refreshing={refreshing}
        />
      </SafeAreaView>
    </LayoutContainer>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    marginBottom: 40,
  },
  item: {
    paddingVertical: 10,
  },
  title: {
    fontSize: 16,
  },
});

const HistoryHeaderContainer = styled(HeaderContainer)`
  flex: 0.2;
`;

export default History;
