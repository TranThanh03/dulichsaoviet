import { ROUTERS } from "./utils/router";
import { Routes, Route, createBrowserRouter, RouterProvider, useNavigate } from "react-router-dom";
import { useEffect } from "react";
import * as usersPage from "pages/users";
import * as adminPage from "pages/admin";
import * as errorPage from "component/error";

const NotFoundRedirect = () => {
    const navigate = useNavigate();

    useEffect(() => {
        navigate("/error/404", { replace: true });
    }, [navigate]);

    return <errorPage.Page404 />;
};

const NotFoundManageRedirect = () => {
    const navigate = useNavigate();

    useEffect(() => {
        navigate("/manage/error/404", { replace: true });
    }, [navigate]);

    return <errorPage.ManagePage404 />;
};

const RenderRouter = () => {
    const customRouters = [
        {
            path: ROUTERS.USER.HOME,
            component: <usersPage.HomePage />
        },
        {
            path: ROUTERS.USER.LOGINPAGE,
            component: <usersPage.LoginPage />
        },
        {
            path: ROUTERS.USER.REGISTERPAGE,
            component: <usersPage.RegisterPage />
        },
        {
            path: ROUTERS.USER.PROFILE,
            component: <usersPage.ProfilePage />
        },
        {
            path: ROUTERS.USER.PASSWORD,
            component: <usersPage.PasswordPage />
        },
        {
            path: ROUTERS.USER.NEWS,
            component: <usersPage.NewsPage />
        },
        {
            path: ROUTERS.USER.NEWSDETAIL,
            component: <usersPage.NewsDetailPage />
        },
        {
            path: ROUTERS.USER.HOTELS,
            component: <usersPage.HotelPage />
        },
        {
            path: ROUTERS.USER.HOTELSDETAIL,
            component: <usersPage.HotelDetailPage />
        },
        {
            path: ROUTERS.USER.TOURS,
            component: <usersPage.TourPage />
        },
        {
            path: ROUTERS.USER.TOURCATEGORY,
            component: <usersPage.TourCategoryPage />
        },
        {
            path: ROUTERS.USER.TOURLIST,
            component: <usersPage.TourListPage />
        },
        {
            path: ROUTERS.USER.TOURSDETAIL,
            component: <usersPage.TourDetailPage />
        },
         {
            path: ROUTERS.USER.GUIDES,
            component: <usersPage.GuidePage />
        },
        {
            path: ROUTERS.USER.GUIDESDETAIL,
            component: <usersPage.GuideDetailPage />
        },
        {
            path: ROUTERS.USER.GUIDESLIST,
            component: <usersPage.GuideListPage />
        },
        {
            path: ROUTERS.USER.ORDERS,
            component: <usersPage.OrderPage />
        },
        {
            path: ROUTERS.USER.CALENDARS,
            component: <usersPage.CalendarPage />
        },
        {
            path: ROUTERS.USER.ORDERSMESSAGE,
            component: <usersPage.MessagePage />
        },
        {
            path: ROUTERS.USER.SEARCH,
            component: <usersPage.SearchPage />
        },
        {
            path: ROUTERS.USER.ACTIVATE,
            component: <usersPage.ActivatePage />
        },
        {
            path: ROUTERS.ADMIN.HOME,
            component: <adminPage.HomePage />
        },
        {
            path: ROUTERS.ADMIN.LOGINPAGE,
            component: <adminPage.LoginPage />
        },
        {
            path: ROUTERS.ADMIN.CUSTOMERSPAGE,
            component: <adminPage.CustomerPage />
        },
        {
            path: ROUTERS.ADMIN.TOURSPAGE,
            component: <adminPage.TourPage />
        },
        {
            path: ROUTERS.ADMIN.TOURINSERTPAGE,
            component: <adminPage.TourInsertPage />
        },
        {
            path: ROUTERS.ADMIN.TOURUPDATEPAGE,
            component: <adminPage.TourUpdatePage />
        },
        {
            path: ROUTERS.ADMIN.GUIDESPAGE,
            component: <adminPage.GuidePage />
        },
        {
            path: ROUTERS.ADMIN.GUIDEINSERTPAGE,
            component: <adminPage.GuideInsertPage />
        },
        {
            path: ROUTERS.ADMIN.GUIDEUPDATEPAGE,
            component: <adminPage.GuideUpdatePage />
        },
        {
            path: ROUTERS.ADMIN.ASSIGNMENTSPAGE,
            component: <adminPage.AssignmentPage />
        },
        {
            path: ROUTERS.ADMIN.CALENDARSPAGE,
            component: <adminPage.CalendarPage />
        },
        {
            path: ROUTERS.ADMIN.CALENDARDETAILSPAGE,
            component: <adminPage.CalendarDetailPage />
        },
        {
            path: ROUTERS.ADMIN.PROFILEPAGE,
            component: <adminPage.ProfilePage />
        },
        {
            path: ROUTERS.ADMIN.PASSWORDPAGE,
            component: <adminPage.PasswordPage />
        },
        {
            path: ROUTERS.ADMIN.DASHBOARDPAGE,
            component: <adminPage.DashboardPage />
        },
        {
            path: ROUTERS.ERROR.ERROR500,
            component: <errorPage.Page500 />
        },
        {
            path: ROUTERS.ERROR.MANAGEERROR500,
            component: <errorPage.ManagePage500 />
        }
    ];

    const isAdminRoute = window.location.pathname.startsWith("/manage");

    return (
        isAdminRoute ? (
            <adminPage.MasterLayout>
                <Routes>
                    {customRouters
                        .filter(route => route.path.startsWith("/manage"))
                        .map((item, key) => (
                            <Route key={key} path={item.path} element={item.component} />
                        ))}
                    <Route path="*" element={<NotFoundManageRedirect />} />
                </Routes>
            </adminPage.MasterLayout>
        ) : (
            <usersPage.MasterLayout>
                <Routes>
                    {customRouters
                        .filter(route => !route.path.startsWith("/manage"))
                        .map((item, key) => (
                            <Route key={key} path={item.path} element={item.component} />
                        ))}
                    <Route path="*" element={<NotFoundRedirect />} />
                </Routes>
            </usersPage.MasterLayout>
        )
    );
};

const router = createBrowserRouter([
    {
        path: "/*",
        element: <RenderRouter />
    }
], {
    future: {
        v7_relativeSplatPath: true,
        v7_startTransition: true,
    }
});

const RouterCustom = () => {
    return <RouterProvider router={router} />;
};

export default RouterCustom;