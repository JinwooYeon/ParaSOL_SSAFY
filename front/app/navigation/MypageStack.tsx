import { createNativeStackNavigator } from "@react-navigation/native-stack";
import Mypage from "../screens/Mypage";
import AuthBio from "../screens/Mypage/authBio";
import ConnectAccount from "../screens/Mypage/ConnectAccount";
import Oauth from "../screens/Mypage/Oauth";
import Profile from "../screens/Mypage/Profile";
import Service from "../screens/Mypage/Service";

const Stack = createNativeStackNavigator();

const MypageStack = () => {
  return (
    <Stack.Navigator
      screenOptions={{
        contentStyle: { backgroundColor: "white" },
        animation: "slide_from_right",
        headerShown: false,
      }}
      initialRouteName="MypageMain"
    >
      <Stack.Screen name="MypageMain" component={Mypage} />
      <Stack.Screen name="Profile" component={Profile} />
      <Stack.Screen name="ConnectAccount" component={ConnectAccount} />
      <Stack.Screen name="Oauth" component={Oauth} />
      <Stack.Screen name="AuthBio" component={AuthBio} />
      <Stack.Screen name="Service" component={Service} />
    </Stack.Navigator>
  );
};

export default MypageStack;
