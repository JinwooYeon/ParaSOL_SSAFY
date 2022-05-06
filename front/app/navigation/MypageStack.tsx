import { createNativeStackNavigator } from "@react-navigation/native-stack";
import Mypage from "../screens/Mypage";
import AuthBio from "../screens/AuthBio";
import ConnectAccount from "../screens/ConnectAccount";
import Oauth from "../screens/Oauth";
import Profile from "../screens/Profile";
import Service from "../screens/Service";

const Stack = createNativeStackNavigator();

const MypageStack = () => {
  return (
    <Stack.Navigator
      initialRouteName="MypageMain"
      screenOptions={{
        contentStyle: { backgroundColor: "white" },
        animation: "slide_from_right",
        headerShown: false,
      }}
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
