import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";
import History from "../screens/History";
import { MaterialCommunityIcons } from "@expo/vector-icons";
import { mainBlue } from "../color";
import { useState } from "react";
import PayStack from "./PayStack";
import MypageStack from "./MypageStack";
import Benefit from "../screens/Benefit";
import HomeStack from "./HomeStack";

interface PropsType {
  setLogin: (a: any) => void;
}

const Tab = createBottomTabNavigator();

const Tabs: React.FC<PropsType> = ({ setLogin }) => {
  const [balance, setBalance] = useState("999,999");

  return (
    <Tab.Navigator
      initialRouteName="Home"
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
        name="Pay"
        options={{
          title: "페이",
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
        {(props) => <PayStack {...props} balance={balance} />}
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
        {(props) => <HomeStack {...props} balance={balance} />}
      </Tab.Screen>
      <Tab.Screen
        name="Benefit"
        component={Benefit}
        options={{
          title: "혜택",
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
      />
      <Tab.Screen
        name="Mypage"
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
      >
        {(props) => <MypageStack {...props} setLogin={setLogin} />}
      </Tab.Screen>
    </Tab.Navigator>
  );
};

export default Tabs;
