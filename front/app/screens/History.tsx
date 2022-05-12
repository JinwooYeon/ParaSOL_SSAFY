import BalanceBox from "../components/BalanceBox";
import { HeaderContainer, LayoutContainer } from "./styled";
import {
  FlatList,
  SafeAreaView,
  StatusBar,
  StyleSheet,
  Text,
  TouchableOpacity,
  View,
} from "react-native";
import { useState } from "react";
import styled from "styled-components/native";

interface PropsType {
  balance: string;
}
interface ItemPropsType {
  item: any;
}

const History: React.FC<PropsType> = ({ balance }) => {
  // useState
  const [refreshing, setRefreshing] = useState(false);
  const [selectedId, setSelectedId] = useState(null);
  const [total, setTotal] = useState("-1,234,000");
  const [data, setData] = useState([
    {
      id: "05월 10일 12시 10분", // 날짜 데이터 (기본키, key로 사용할 예정)
      title: "연어를 덮밥 서초점",
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
      id: "05월 4일 12시 10분", // 날짜 데이터
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

  const renderItem = ({ item }: any) => {
    return <Item item={item} />;
  };

  // method
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
          // backgroundColor: "blue",
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
          extraData={selectedId}
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
  flex: 0.3;
`;

export default History;
