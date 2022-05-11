import { createNativeStackNavigator } from "@react-navigation/native-stack";
import Mypage from "../screens/MypageStack/Mypage";
import AuthBio from "../screens/MypageStack/AuthBio";
import ConnectAccount from "../screens/MypageStack/ConnectAccount";
import Oauth from "../screens/MypageStack/Oauth";
import Profile from "../screens/MypageStack/Profile";
import Service from "../screens/MypageStack/Service";
import Delete from "../screens/MypageStack/Delete";

interface PropsType {
  setLogin: (a: any) => void;
}

const Stack = createNativeStackNavigator();

const MypageStack: React.FC<PropsType> = ({ setLogin }) => {
  return (
    <Stack.Navigator
      initialRouteName="MypageMain"
      screenOptions={{
        contentStyle: { backgroundColor: "white" },
        animation: "slide_from_right",
        headerShown: false,
      }}
    >
      <Stack.Screen name="MypageMain">
        {(props) => <Mypage {...props} setLogin={setLogin} />}
      </Stack.Screen>
      <Stack.Screen name="Profile" component={Profile} />
      <Stack.Screen name="ConnectAccount" component={ConnectAccount} />
      <Stack.Screen name="Oauth" component={Oauth} />
      <Stack.Screen name="AuthBio" component={AuthBio} />
      <Stack.Screen name="Service" component={Service} />
      <Stack.Screen name="Delete">
        {(props) => <Delete {...props} setLogin={setLogin} />}
      </Stack.Screen>
    </Stack.Navigator>
  );
};

export default MypageStack;
