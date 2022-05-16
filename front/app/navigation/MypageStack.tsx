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
  // 계좌 연결 정보
  bankInfo: any;
  // 계좌 연결 정보 set
  setBankInfo: (a: any) => void;
  // 새로운 인증 토큰 발급
  getNewToken: () => Promise<any>;
}

// Component _ MypageStack
const MypageStack: React.FC<PropsType> = ({
  setLogin,
  bankInfo,
  setBankInfo,
  getNewToken,
}) => {
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
      <Stack.Screen name="Profile">
        {(props) => <Profile {...props} getNewToken={getNewToken} />}
      </Stack.Screen>
      {/* 계좌 관리하기 */}
      <Stack.Screen name="ConnectAccount">
        {(props) => (
          <ConnectAccount
            {...props}
            bankInfo={bankInfo}
            setBankInfo={setBankInfo}
            getNewToken={getNewToken}
          />
        )}
      </Stack.Screen>
      {/* OTP 정보 등록 */}
      <Stack.Screen name="Oauth" component={Oauth} />
      {/* 생체인증 정보 등록 */}
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
