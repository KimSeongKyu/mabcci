export const baseUrl = 'http://localhost:8080';

/* AuthAPI Url */
export const LoginUrl = '/auth/login';
export const LogoutUrl = '/auth/logout';
export const SignupUrl = '/api/members';
export const CheckNicknameUrl = '/api/members/check/';

/* OOTDAPI Url */
export const OOTDWriteUrl = '/api/ootds';
export const OOTDUpdateUrl = '/api/ootds/';
export const OOTDDeleteUrl = '/api/ootds/';
export const OOTDDetailUrl = '/api/ootds/';
export const OOTDSearchUrl = '/api/ootds?search=?&size=20&page=?';
export const OOTDListUrl = '/api/ootds?size=20&page=?';
export const OOTDMyPageListUrl = '';
export const OOTDCommentCreateUrl = '/api/ootd/comments';
export const OOTDCommentUpdateUrl = '/api/ootd/comments/';
export const OOTDCommentDeleteUrl = '/api/ootd/comments/';
export const OOTDCommentReadUrl = '/api/ootd/';
export const OOTDLikeUrl = '/api/ootd/';

/* MabcciAPI Url */
export const AllMabcciUrl = '/api/members/mabcci';
export const PopularMabcciUrl = '/api/members/mabcci/popular';

/* MypageAPI Url */
export const MypageReadUrl = '/api/members/mypage';
export const MypageUpdateUrl = '/api/members/update';
export const FollowUrl = '/api/follow';
export const UnFollowUrl = '/api/unfollow';
export const FollowListUrl = '/api/';

/* Suggestion Url */
export const SuggestionWriteUrl = '/api/proposals';
export const SuggestionReadUrl = '/api/proposals/';
export const SuggestSuggestionListUrl =
  '/api/proposals?filter=suggested&nickname=';
export const ReceivedSuggestionListUrl =
  '/api/proposals?filter=received&nickname=';
