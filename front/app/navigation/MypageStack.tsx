import { createNativeStackNavigator } from "@react-navigation/native-stack";
import Mypage from "../screens/Mypage";
import AuthBio from "../screens/AuthBio";
import ConnectAccount from "../screens/ConnectAccount";
import Oauth from "../screens/Oauth";
import Profile from "../screens/User/Profile";
import Service from "../screens/Service";
import Delete from "../screens/User/Delete";
import UpdateProfile from "../screens/User/UpdateProfile";

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
      <Stack.Screen name="UpdateProfile" component={UpdateProfile} />
      <Stack.Screen name="ConnectAccount" component={ConnectAccount} />
      <Stack.Screen name="Oauth" component={Oauth} />
      <Stack.Screen name="AuthBio" component={AuthBio} />
      <Stack.Screen name="Service" component={Service} />
      <Stack.Screen name="Delete" component={Delete} />
    </Stack.Navigator>
  );
};

export default MypageStack;
