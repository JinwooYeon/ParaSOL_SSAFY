import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";
import Charge from "../screens/Charge";
import History from "../screens/History";
import Home from "../screens/Home";
import Mypage from "../screens/Mypage";
import Withdraw from "../screens/Withdraw";
import { MaterialCommunityIcons } from "@expo/vector-icons";
import { mainBlue } from "../color";

const Tab = createBottomTabNavigator();

const Tabs = () => (
  <Tab.Navigator
    initialRouteName="홈"
    screenOptions={{
      tabBarLabelStyle: { fontWeight: "bold" },
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
      name="충전"
      component={Charge}
      options={{
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
    />
    <Tab.Screen
      name="출금"
      component={Withdraw}
      options={{
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
      name="홈"
      component={Home}
      options={{
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
    />
    <Tab.Screen
      name="거래 내역"
      component={History}
      options={{
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
    />
    <Tab.Screen
      name="내 정보"
      component={Mypage}
      options={{
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

export default Tabs;
