import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";
import History from "../screens/History";
import Home from "../screens/Home";
import { MaterialCommunityIcons } from "@expo/vector-icons";
import { mainBlue } from "../color";
import { useState } from "react";
import ChargeStack from "./ChargeStack";
import MypageStack from "./MypageStack";
import WithdrawStack from "./WithdrawStack";

const Tab = createBottomTabNavigator();

const Tabs = () => {
  const [balance, setBalance] = useState("999,999");

  return (
    <Tab.Navigator
      initialRouteName="홈"
      screenOptions={{
        tabBarLabelStyle: { fontWeight: "bold", fontSize: 13 },
        tabBarActiveTintColor: mainBlue,
        tabBarInactiveTintColor: "black",
        headerShown: false,
        tabBarHideOnKeyboard: true,
      }}
      sceneContainerStyle={{
        backgroundColor: "white",
      }}
    >
      <Tab.Screen
        name="Charge"
        options={{
          title: "충전",
          tabBarIcon: ({ focused, color, size }) => {
            return (
              <MaterialCommunityIcons
                name="cash-plus"
                size={size}
                color={color}
              />
            );
          },
        }}
      >
        {(props) => <ChargeStack {...props} balance={balance} />}
      </Tab.Screen>
      <Tab.Screen
        name="Withdraw"
        options={{
          title: "출금",
          tabBarIcon: ({ focused, color, size }) => {
            return (
              <MaterialCommunityIcons
                name="lightning-bolt-outline"
                size={size}
                color={color}
              />
            );
          },
        }}
      >
        {(props) => <WithdrawStack {...props} balance={balance} />}
      </Tab.Screen>
      <Tab.Screen
        name="Home"
        options={{
          title: "홈",
          tabBarIcon: ({ focused, color, size }) => {
            return (
              <MaterialCommunityIcons
                name="home-outline"
                size={size}
                color={color}
              />
            );
          },
        }}
      >
        {(props) => <Home {...props} balance={balance} />}
      </Tab.Screen>
      <Tab.Screen
        name="History"
        options={{
          title: "거래 내역",
          tabBarIcon: ({ focused, color, size }) => {
            return (
              <MaterialCommunityIcons
                name="newspaper-variant-multiple-outline"
                size={size}
                color={color}
              />
            );
          },
        }}
      >
        {(props) => <History {...props} balance={balance} />}
      </Tab.Screen>
      <Tab.Screen
        name="Mypage"
        component={MypageStack}
        options={{
          title: "내 정보",
          tabBarIcon: ({ focused, color, size }) => {
            return (
              <MaterialCommunityIcons
                name="account-outline"
                size={size}
                color={color}
              />
            );
          },
        }}
      />
    </Tab.Navigator>
  );
};

export default Tabs;
