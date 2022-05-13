import { createNativeStackNavigator } from "@react-navigation/native-stack";
import Mypage from "../screens/MypageStack/Mypage";
import AuthBio from "../screens/MypageStack/AuthBio";
import ConnectAccount from "../screens/MypageStack/ConnectAccount";
import Oauth from "../screens/MypageStack/Oauth";
import Profile from "../screens/MypageStack/Profile";
import Service from "../screens/MypageStack/Service";
import Delete from "../screens/MypageStack/Delete";

const Stack = createNativeStackNavigator();

interface PropsType {
  // 로그인 여부 set
  setLogin: (a: any) => void;
}

// Component _ MypageStack
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
      {/* 내 정보 첫 스크린 */}
      <Stack.Screen name="MypageMain">
        {(props) => <Mypage {...props} setLogin={setLogin} />}
      </Stack.Screen>
      {/* 회원정보 */}
      <Stack.Screen name="Profile" component={Profile} />
      {/* 계좌 관리하기 */}
      <Stack.Screen name="ConnectAccount" component={ConnectAccount} />
      {/* 공동인증 발급/재발급 */}
      <Stack.Screen name="Oauth" component={Oauth} />
      {/* 생체인증 발급/재발급 */}
      <Stack.Screen name="AuthBio" component={AuthBio} />
      {/* 고객 문의 */}
      <Stack.Screen name="Service" component={Service} />
      {/* 회원 탈퇴 */}
      <Stack.Screen name="Delete">
        {(props) => <Delete {...props} setLogin={setLogin} />}
      </Stack.Screen>
    </Stack.Navigator>
  );
};

export default MypageStack;
