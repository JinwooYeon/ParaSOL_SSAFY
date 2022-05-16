import MypageMenuBox from "../../components/MypageMenuBox";
import { LayoutContainer, HeaderText, MypageMenuContainer } from "../styled";

interface PropsType {
  // 로그인 여부 set
  setLogin: (a: boolean) => void;
  // stack naviagation
  navigation: any;
}

// Component _ Mypage
const Mypage: React.FC<PropsType> = ({ navigation, setLogin }) => {
  // const
  // 내 정보 메뉴 데이터
  const menus = [
    "회원정보",
    "계좌 관리하기",
    "공동인증 발급/재발급",
    "생체인증 발급/재발급",
    "고객 문의",
    "회원 탈퇴",
    "로그아웃",
  ];

  return (
    <LayoutContainer>
      <HeaderText>내 정보</HeaderText>
      <MypageMenuContainer>
        {menus.map((menu, idx) => (
          <MypageMenuBox
            key={idx}
            menu={menu}
            navigation={navigation}
            setLogin={setLogin}
          />
        ))}
      </MypageMenuContainer>
    </LayoutContainer>
  );
};

export default Mypage;
